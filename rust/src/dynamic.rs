use crate::points::{DynamicPoint, Point, Points};
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
/// Box<dyn ...> {
///     vtable_pointer: 8B,
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
/// {dv}{dv}{dv}...   (Box<dyn DynamicPoint>)  contiguous heap allocation of fat pointers
///  |   \    |
/// {xy} {xy} | ...  (Point) separate heap objects
///          /
/// {vtable}  once per dynamic type, contains size, alignment and Trait-Methods
/// ```
pub struct DynamicPoints {
    points: Vec<Box<dyn DynamicPoint>>,
}

impl FromIterator<Point> for DynamicPoints {
    fn from_iter<Iter: IntoIterator<Item=Point>>(iterator: Iter) -> Self {
        let points = iterator.into_iter()
            .map(|point| Box::new(point) as Box<dyn DynamicPoint>)
            .collect();
        Self {
            points
        }
    }
}

impl Points for DynamicPoints {
    fn name() -> &'static str {
        "DynamicPoints"
    }

    fn average_length(&self) -> f64 {
        let total_length = self.points.iter()
            .map(|point| point.length())
            .sum::<u64>();

        (total_length as f64) / (self.points.len() as f64)
    }

    fn size_estimate(&self) -> usize {
        std::mem::size_of::<Point>() * self.points.len() + std::mem::size_of::<Box<dyn DynamicPoint>>() * self.points.len()
    }
}
