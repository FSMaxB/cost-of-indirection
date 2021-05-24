use std::time::{Instant, Duration};

struct Point {
    x: u32,
    y: u32,
}

impl Point {
    // Length according to taxicab geometry
    fn length(&self) -> u64 {
        u64::from(self.x) + u64::from(self.y)
    }
}

struct Points {
    points: Vec<Point>,
}

impl Points {
    fn new(size: u32) -> Self {
        let points = (0..size)
            .into_iter()
            .map(|number| Point { x: number / 10, y: number % 10})
            .collect();

        Self {
            points
        }
    }

    fn average_length(&self) -> f64 {
        let total_length = self.points.iter()
            .map(Point::length)
            .sum::<u64>();

        (total_length as f64) / (self.points.len() as f64)
    }
}

fn compute_and_time<Closure, Output>(closure: Closure) -> (Output, Duration)
where
    Closure: FnOnce() -> Output,
{
    let before = Instant::now();
    let output = closure();
    (output, Instant::now() - before)
}

fn main() {
    const SIZE: u32 = 300_000_000;
    let points = Points::new(SIZE);

    let (average_length, duration) = compute_and_time(|| points.average_length());
    println!("Average length via for loop: {} ({}s)", average_length, duration.as_secs_f64());
}
