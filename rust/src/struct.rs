use crate::points::{Point, Points};
use std::iter::FromIterator;

pub struct StructPoints {
    points: Vec<Point>,
}

impl FromIterator<Point> for StructPoints {
    fn from_iter<Iter: IntoIterator<Item=Point>>(iterator: Iter) -> Self {
        let points = iterator.into_iter().collect();
        Self {
            points
        }
    }
}

impl Points for StructPoints {
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
