import java.util.*;
import java.io.*;

public class RewardPoints {
	
	public static int calculateRewardPoints(int amount)
	{
		int pnts=0;
		if(amount <= 50)
			return pnts;
		
		if(amount <=100)
			return amount - 50;
		if(amount > 100)
		{
			pnts =  2 * (amount - 100);
			pnts += ((int)(amount/100))*50;
			
		}
		
		return pnts;
	}
	public static void main(String args[]) throws IOException{
		
		Scanner in= new Scanner(System.in);
		FileReader f= new FileReader("data.txt");// id date of transaction amount of transaction
		BufferedReader br = new BufferedReader(f);
		String line = br.readLine();
		//storing detils based on customer id.
		//later storing details based on month in each hashmap
		HashMap<Integer,HashMap<String,Integer>> customerDetails = new HashMap();
		while(line!=null)
		{
			String details[] = line.split("\\s+");
			int id =  Integer.parseInt(details[0]);
			String month = details[1].split("/")[1]; //storing only month as data set is expected to be within 3 month range.
			int amount = Integer.parseInt(details[2]);
			
			if(customerDetails.containsKey(id))
			{
				HashMap<String,Integer> detail = customerDetails.get(id);
				if(detail.containsKey(month))
				{
					int amountpre = detail.get(month);
					detail.put(month, amountpre+amount);
					customerDetails.put(id, detail);
				}
				else
				{
					detail.put(month,amount);
				}
			}
			else
			{
				HashMap<String,Integer> detail  = new HashMap<String, Integer>();
				detail.put(month,amount);
				customerDetails.put(id, detail);
			}
			
			line =  br.readLine();
		}
		
		
		
		for( Map.Entry<Integer, HashMap<String,Integer>> entry : customerDetails.entrySet() ){
			int cusID = entry.getKey();
			System.out.println("Customer ID :"+cusID);
			int totalrewardpoints = 0;
			for( Map.Entry<String,Integer> entrychild : entry.getValue().entrySet() ){
				String month1 =  entrychild.getKey();
				int amt = entrychild.getValue();
			    int rewardpoints = calculateRewardPoints(amt);
			    System.out.println("for "+month1+"  month reward points are "+rewardpoints);
			    totalrewardpoints+=rewardpoints;
			}
			System.out.println("total reward points:"+totalrewardpoints);
			
		}
		
		
	}

}
