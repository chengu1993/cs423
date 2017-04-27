import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class implements the LRU page-replacement strategy.
 *
 */

public class LRU_1 extends ReplacementAlgorithm
{
    // LRU list of page frames
    private LRUList frameList;

    /**
     * @param pageFrameCount - the number of physical page frames
     */
    public LRU_1 (int pageFrameCount) {
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
        LinkedList<Integer> list;
        Map<Integer, Integer> map;


        LRUList(int pageFrameCount) {
            pageFrameList = new int[pageFrameCount];
            list = new LinkedList<>();
            map = new HashMap<>();
            // we initialize each entry to -1 to indicate initial value is invalid.
            java.util.Arrays.fill(pageFrameList,-1);
            elementCount = 0;

        }

        /**
         * @param pageNumber the number of the page to be
         *	inserted into the page frame list.
         */
        void insert(int pageNumber) {

            if(!map.containsKey(pageNumber)) {
                int frameNumber;
                if(elementCount < pageFrameCount) {
                    frameNumber = elementCount++;

                } else {
                    frameNumber = list.poll();
                    map.remove(pageFrameList[frameNumber]);

                }
                map.put(pageNumber, frameNumber);
                list.add(frameNumber);
                pageFrameList[frameNumber] = pageNumber;
                pageFaultCount++;
            } else {
                int frameNumber = map.get(pageNumber);
                list.remove(Integer.valueOf(frameNumber));
                list.add(frameNumber);
            }
        }

        void dump() {
            for (int i = 0; i < pageFrameCount; i++)
                System.out.print("["+i+"]"+pageFrameList[i]+", ");
            System.out.println(" element count = " + elementCount);
        }

    }
}
