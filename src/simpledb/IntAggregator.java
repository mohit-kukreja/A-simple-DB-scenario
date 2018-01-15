package simpledb;

/**
 * An {@code IntAggregator} computes some aggregate value over a set of {@code IntField}s.
 */
public class IntAggregator implements Aggregator {

	/**
	 * A {@code IntAggregatorImpl} instance.
	 */
	IntAggregatorImpl impl;

	/**
	 * Constructs an {@code IntAggregator}.
	 * 
	 * @param gbfield
	 *            the 0-based index of the group-by field in the tuple, or {@code NO_GROUPING} if there is no grouping
	 * @param gbfieldtype
	 *            the type of the group by field (e.g., {@code Type.INT_TYPE}), or {@code null} if there is no grouping
	 * @param afield
	 *            the 0-based index of the aggregate field in the tuple
	 * @param what
	 *            the aggregation operator
	 */
	public IntAggregator(int gbfield, Type gbfieldtype, int afield, Op what) {
		// some code goes here
		throw new UnsupportedOperationException("Implement this");
	}

	/**
	 * Merges a new {@code Tuple} into the aggregate, grouping as indicated in the constructor.
	 * 
	 * @param tup
	 *            the {@code Tuple} containing an aggregate field and a group-by field
	 */
	public void merge(Tuple tup) {
		// some code goes here
		throw new UnsupportedOperationException("Implement this");
	}

	/**
	 * Creates a {@code DbIterator} over group aggregate results.
	 *
	 * @return a {@code DbIterator} whose tuples are the pair ({@code groupVal}, {@code aggregateVal}) if using group,
	 *         or a single ({@code aggregateVal}) if no grouping. The {@code aggregateVal} is determined by the type of
	 *         aggregate specified in the constructor.
	 */
	public DbIterator iterator() {
		// some code goes here
		throw new UnsupportedOperationException("Implement this");
	}

	/**
	 * An {@code IntAggregatorImpl} computes some aggregate value over a set of {@code Field}s.
	 */
	abstract class IntAggregatorImpl {

		// some code goes here
	}

	// some code goes here

	/**
	 * Clears this {@code IntAggregator}.
	 */
	@Override
	public void clear() {
		// some code goes here
		throw new UnsupportedOperationException("Implement this");
	}
}
