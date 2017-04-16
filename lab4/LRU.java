import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the LRU page-replacement strategy.
 *
 */

public class LRU extends ReplacementAlgorithm
{
	// LRU list of page frames
	private LRUList frameList;

	/**
	 * @param pageFrameCount - the number of physical page frames
	 */
	public LRU(int pageFrameCount) {
		super(pageFrameCount);
		frameList = new LRUList(pageFrameCount);
	}

	/**
	 * Insert a page into a page frame.
	 */
	public void insert(int pageNumber) {
		frameList.insert(pageNumber);
		frameList.dump();
    }
		
	class LRUList
	{
		// the page frame list
		int[] pageFrameList;

		// the number of elements in the page frame list
		int elementCount;

		// the last page inserted
		int lastInserted = -1;

		int[] counter;




		LRUList(int pageFrameCount) {
			pageFrameList = new int[pageFrameCount];
			counter = new int[pageFrameCount];

            // we initialize each entry to -1 to indicate initial value is invalid.
            java.util.Arrays.fill(pageFrameList,-1);
            java.util.Arrays.fill(counter, Integer.MAX_VALUE);
			elementCount = 0;

		}

		/**
		 * @param pageNumber the number of the page to be 
		 *	inserted into the page frame list.
		 */
		void insert(int pageNumber) {
			int frameNumber = search(pageNumber);
			if(frameNumber == -1) {

				int frameNumberToInsert = elementCount < pageFrameCount ? elementCount++ : getOldest();
				updateCounter(frameNumberToInsert);
				pageFrameList[frameNumberToInsert] = pageNumber;
				pageFaultCount++;
			} else {
				updateCounter(frameNumber);
			}
        }

		void dump() {
			for (int i = 0; i < pageFrameCount; i++)
				System.out.print("["+i+"]"+pageFrameList[i]+", ");
			System.out.println(" element count = " + elementCount);
		}


		void updateCounter(int frameNumber) {
			for(int i = 0; i < pageFrameCount; i++) {
				if(i == frameNumber) counter[i] = 0;
				else counter[i]++;
			}
		}

		int getOldest() {
			int oldest = 0;
			for(int i = 1; i < pageFrameCount; i++) {
				if(counter[i] > counter[oldest]) {
					oldest = i;
				}
			}
			return oldest;
		}

		/**
		 * Searches for page pageNumber in the page frame list
		 * @return non-negative integer if pageNumber was found
		 * @return -1 if pageNumber was not found
		 */
		int search(int pageNumber) {
			int returnVal = -1;

			for (int i = 0; i < pageFrameCount; i++) {
				if (pageNumber == pageFrameList[i]) {
					returnVal = i;
					break;
				}
			}
			return returnVal;
		}
	}
}
