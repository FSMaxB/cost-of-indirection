use crate::points::{Point, Points};
use std::iter::FromIterator;

/// Memory Layout:
///
/// ```
/// Vec {
///     heap_pointer: 8B,
///     capacity: 8B,
///     size: 8B,
/// }
///
/// Point {
///     x: 4B,
///     y: 4B,
/// }
///
/// {points} (Vec)
///  |
/// {xy}{xy}{xy}...   (Point)  1 contiguous heap allocation
/// ```
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
        "StructPoints"
    }

    fn average_length(&self) -> f64 {
        let total_length = self.points.iter()
            .map(Point::length)
            .sum::<u64>();

        (total_length as f64) / (self.points.len() as f64)
    }

    fn size_estimate(&self) -> usize {
        std::mem::size_of::<Point>() * self.points.len()
    }
}
