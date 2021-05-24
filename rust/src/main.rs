use std::time::{Instant, Duration};
use crate::inline::InlinePoints;
use crate::points::{Points, Point};

mod inline;
mod points;

fn main() {
    const SIZE: u32 = 300_000_000;

    test_implementation::<InlinePoints>(SIZE);
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