use std::time::{Instant, Duration};
use crate::r#struct::StructPoints;
use crate::points::{Points, Point, DynamicPoint};
use crate::boxed::BoxedPoints;
use crate::dynamic::DynamicPoints;

mod r#struct;
mod boxed;
mod dynamic;
mod points;

fn main() {
    const SIZE: u32 = 300_000_000;

    println!("Point: {}B", std::mem::size_of::<Point>());
    println!("Box<Point>: {}B", std::mem::size_of::<Box<Point>>());
    println!("Box<dyn DynamicPoint>: {}B", std::mem::size_of::<Box<dyn DynamicPoint>>());
    println!();

    test_implementation::<StructPoints>(SIZE);
    test_implementation::<BoxedPoints>(SIZE);
    test_implementation::<DynamicPoints>(SIZE);
}

fn test_implementation<Implementation: Points>(size: u32) {
    let point_iterator = (0..size)
        .into_iter().map(Point::from_number);
    let points = Implementation::from_iter(point_iterator);

    const MIB: usize = 1024 * 1024;
    let size_estimate = points.size_estimate();

    let (average_length, duration) = compute_and_time(|| points.average_length());
    println!("{}: Average length: {} ({}s) (ca. {}MiB)", Implementation::name(), average_length, duration.as_secs_f64(), size_estimate / MIB);
}

fn compute_and_time<Closure, Output>(closure: Closure) -> (Output, Duration)
    where
        Closure: FnOnce() -> Output,
{
    let before = Instant::now();
    let output = closure();
    (output, Instant::now() - before)
}
