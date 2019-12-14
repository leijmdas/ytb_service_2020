package ytb.check;

public class QuickSort {


    static void ini(int[] nums) {

        for (int i = 0; i < 10; i++) {
            nums[i] = 100 + (int) (Math.random() * 100);
        }

    }

    static void qsort(int[] nums) {
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    static int bsearch(int[] nums,int num, int istart, int iend) {
        int m = iend - istart;
        m = istart + m / 2;
        System.out.println("m=" + m + "," + istart + "," + iend);
        if (istart == iend && m == 0) {
            return -1;
        }
        if (num == nums[m]) {
            return m;
        } else if (num < nums[m]) {
            return bsearch(nums,num, istart, m);
        } else {
            return bsearch(nums,num, m, iend);
        }


    }

    public static void main(String[] args) {
        int[] nums = new int[10];
        ini(nums);
        int num = nums[0];
        qsort(nums);
        System.err.println("num=" + num);
        for (int i : nums) {
            System.out.println(i);
        }

        int pos = bsearch(nums,-1, 0, 9);
        pos = bsearch(nums, num, 0, 9);
        System.out.println("pos=" + pos);

    }

}
