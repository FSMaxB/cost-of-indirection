use crate::points::{Point, Points};
use std::iter::FromIterator;

pub struct InlinePoints {
    points: Vec<Point>,
}

impl FromIterator<Point> for InlinePoints {
    fn from_iter<Iter: IntoIterator<Item=Point>>(iterator: Iter) -> Self {
        let points = iterator.into_iter().collect();
        Self {
            points
        }
    }
}

impl Points for InlinePoints {
    fn name() -> &'static str {
        "InlinePoints"
    }

    fn average_length(&self) -> f64 {
        let total_length = self.points.iter()
            .map(Point::length)
            .sum::<u64>();

        (total_length as f64) / (self.points.len() as f64)
    }
}
