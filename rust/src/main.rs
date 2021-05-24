use std::time::{Instant, Duration};
use crate::r#struct::StructPoints;
use crate::points::{Points, Point};
use crate::boxed::BoxedPoints;
use crate::dynamic::DynamicPoints;

mod r#struct;
mod boxed;
mod dynamic;
mod points;

fn main() {
    const SIZE: u32 = 300_000_000;

    test_implementation::<StructPoints>(SIZE);
    test_implementation::<BoxedPoints>(SIZE);
    test_implementation::<DynamicPoints>(SIZE);
}

fn test_implementation<Implementation: Points>(size: u32) {
    let point_iterator = (0..size)
        .into_iter().map(Point::from_number);
    let points = Implementation::from_iter(point_iterator);

    let (average_length, duration) = compute_and_time(|| points.average_length());
    println!("{}: Average length: {} ({}s)", Implementation::name(), average_length, duration.as_secs_f64());
}

fn compute_and_time<Closure, Output>(closure: Closure) -> (Output, Duration)
    where
        Closure: FnOnce() -> Output,
{
    let before = Instant::now();
    let output = closure();
    (output, Instant::now() - before)
}
