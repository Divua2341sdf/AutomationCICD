package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxTry) {  //if never re-ran it again , then go and run it again
			count++; //we re- ran once, let say first re-ran failed, then second time it will check count is 1 ,so we already tried re-run hence return false
			return true;
		}
		
		return false;
	}

}
