use std::iter::FromIterator;

pub trait Points: FromIterator<Point> {
    fn name() -> &'static str;
    fn average_length(&self) -> f64;
}

pub struct Point {
    pub x: u32,
    pub y: u32,
}

impl Point {
    pub fn from_number(number: u32) -> Self {
        Self { x: number / 10, y: number % 10}
    }

    // Length according to taxicab geometry
    pub fn length(&self) -> u64 {
        // NOTE: Performing the cast AFTER the addition has a BIG impact on performance
        // This is why the hand optimized Java code was initially faster than rust
        u64::from(self.x + self.y)
    }
}
