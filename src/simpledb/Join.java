package simpledb;

import java.util.*;

/**
 * The {@code Join} operator implements the relational join operation.
 */
public class Join extends AbstractDbIterator {

	/**
	 * The TupleDesc for this {@code Join}
	 */
	TupleDesc td;

	/**
	 * The predicate to use to join the children
	 */
	JoinPredicate p;

	/**
	 * Iterator for the left(outer) relation to join
	 */
	DbIterator child1;

	/**
	 * Iterator for the right(inner) relation to join
	 */
	DbIterator child2;

	/**
	 * Current tuple from the left(outer) relation to join
	 */
	Tuple t1 = null;

	/**
	 * Constructs a {@code Join}.
	 *
	 * @param p
	 *            The predicate to use to join the children
	 * @param child1
	 *            Iterator for the left(outer) relation to join
	 * @param child2
	 *            Iterator for the right(inner) relation to join
	 */
	public Join(JoinPredicate p, DbIterator child1, DbIterator child2) {
		this.child1 = child1;
		this.child2 = child2;
		this.p = p;
		td = TupleDesc.combine(child1.getTupleDesc(), child2.getTupleDesc());
	}

	/**
	 * @see simpledb.TupleDesc#combine(TupleDesc, TupleDesc) for possible implementation logic.
	 */
	public TupleDesc getTupleDesc() {
		return td;
	}

	@Override
	public void open() throws DbException, NoSuchElementException, TransactionAbortedException {
		child1.open();
		child2.open();
	}

	@Override
	public void close() {
		child1.close();
		child2.close();
	}

	@Override
	public void rewind() throws DbException, TransactionAbortedException {
		close();
		open();
	}

	/**
	 * Returns the next tuple generated by the join, or null if there are no more tuples. Logically, this is the next
	 * tuple in r1 cross r2 that satisfies the join predicate. There are many possible implementations; the simplest is
	 * a nested loops join.
	 * <p>
	 * Note that the tuples returned from this particular implementation of Join are simply the concatenation of joining
	 * tuples from the left and right relation. Therefore, if an equality predicate is used there will be two copies of
	 * the join attribute in the results. (Removing such duplicate columns can be done with an additional projection
	 * operator if needed.)
	 * <p>
	 * For example, if one tuple is {1,2,3} and the other tuple is {1,5,6}, joined on equality of the first column, then
	 * this returns {1,2,3,1,5,6}.
	 *
	 * @return The next matching tuple.
	 * @see JoinPredicate#filter
	 */
	

	
	protected Tuple readNext() throws TransactionAbortedException, DbException {

		while(child1.hasNext())
		{
			Tuple t;
			if(t1 == null)
			{
				t1=child1.next();
				t=t1;
			}
			else
			{
				t=t1;
			}
			while(child2.hasNext())
			{
				Tuple t2= child2.next();
				if(p.filter(t, t2))
				{
					return concatenate(t, t2);
				}
			}
			t1=null;
			child2.rewind();
		}
			return null;

	}

	/**
	 * Returns the concatenation of the specified tuples.
	 * 
	 * @param t1
	 *            a Tuple
	 * @param t2
	 *            a Tuple
	 * @return the concatenation of the specified tuples.
	 */
	protected Tuple concatenate(Tuple t1, Tuple t2) {
		Tuple t = new Tuple(td);
		for (int i = 0; i < t1.getTupleDesc().numFields(); i++)
			t.setField(i, t1.getField(i));
		for (int j = 0; j < t2.getTupleDesc().numFields(); j++)
			t.setField(j + t1.getTupleDesc().numFields(), t2.getField(j));
		return t;
	}

}
