/**
 * 
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author neha
 * 
 */
public class LongestIncreasingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length!=1){
			System.err.println("file input not found, please give args ");
			return;
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			System.err.println("file input not found, please give args ");
			return;
		}

		String line = null;
		//list to capture max values in the subsequence, where the index is the length of the subsequence
		ArrayList<Integer> a = new ArrayList<Integer>();
		// 0 is nonsense value
		int index = 1;
		a.add(0);
		try {

			while ((line = br.readLine()) != null) {
				//if newline then return;
				if (line.trim().equals(""))
					break;
				int misHeight = Integer.parseInt(line.trim());
				if (a.size() == 1) {
					a.add(index, misHeight);
					index = a.size();
					continue;
				}

				

				// here we know that the last elem of a is the greatest in
				// subseq, so compare misHieght to this elem
				if (a.get(a.size() - 1) < misHeight) {
					a.add(index, misHeight);
					index = a.size();
					// System.out.println(a);
					continue;
				}
				
				//do binary search to find the element that needs to be replaced with current elem
				int maxIndex = 0;
				for (int low = 0, high = a.size(); low < high;) {
					int mid = (low + high) / 2;
					if (a.get(mid) < misHeight) {
						low = mid;

					} else {
						high = mid;
					}
					if ((high - low) == 1) {
						maxIndex = high;
						break;
					}

				}
				a.set(maxIndex, misHeight);
				// System.out.println(a);
				index = a.size();

			}
		} catch (IOException e) {
			System.err.println("error reading file, retry");
			e.printStackTrace();
		}
		System.out.println(index - 1);
	}

}
