use crate::points::{Point, Points};
use std::iter::FromIterator;

pub struct BoxedPoints {
    points: Vec<Box<Point>>,
}

impl FromIterator<Point> for BoxedPoints {
    fn from_iter<Iter: IntoIterator<Item=Point>>(iterator: Iter) -> Self {
        let points = iterator.into_iter()
            .map(Box::new)
            .collect();
        Self {
            points
        }
    }
}

impl Points for BoxedPoints {
    fn name() -> &'static str {
        "BoxedPoints"
    }

    fn average_length(&self) -> f64 {
        let total_length = self.points.iter()
            .map(|point| point.length())
            .sum::<u64>();

        (total_length as f64) / (self.points.len() as f64)
    }
}
