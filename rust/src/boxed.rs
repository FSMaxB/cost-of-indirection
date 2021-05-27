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
/// Box {
///     heap_pointer: 8B,
/// }
///
/// Point {
///     x: 4B,
///     y: 4B,
/// }
///
/// {points} (Vec)
///  |
/// {b}{b}{b}...   (Box<Point>)  contiguous heap allocation of pointers
///  |  \
/// {xy} {xy} ...  (Point) separate heap objects
/// ```
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

    fn size_estimate(&self) -> usize {
        std::mem::size_of::<Point>() * self.points.len() + std::mem::size_of::<Box<Point>>() * self.points.len()
    }
}
