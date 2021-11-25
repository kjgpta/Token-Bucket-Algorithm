package test;
import java.io.*;
import java.lang.*;
import java.util.*;

class InternalDevice{
	public int tokens, maxsize;
	public static int packetsSent_Success = 0;
	public static int packetsSent_Lost = 0;
	int tokenCount = 1;

	InternalDevice(int max){
		tokens = 0;
		maxsize = max;
	}

	synchronized void addToken(int n){
		if(tokens >= maxsize) return;
		tokens += 1;
	    //	System.out.println("Added a token. Total:" + tokens);
	    
	}

	synchronized void sendPacket(int n,String s){
	    //	System.out.println("Packet of size " + n + " arrived from " + s);
		if(n > tokens){
			System.out.println("Packet of size : " + n + " Lost from " +s);
			packetsSent_Lost++;
		}
		else{
			tokens -= n;
			for(int i = tokenCount; i <= (tokenCount + n -1) ;i++)
			{
			    System.out.println("Token " + i + " from " + s +" transmitted");
			    packetsSent_Success++;
			}
			    tokenCount = tokenCount + n ;
		    }
	}
}

class TokenAdder extends Thread{
	InternalDevice b;
	TokenAdder(InternalDevice b){
		this.b = b;
	}
	public void run(){
		while(true){
			b.addToken(1);
			try{
				Thread.sleep(300);
			} catch(Exception e){}
		}
	}
}

class ExternalDevice extends Thread{
	static boolean flag = true;
	InternalDevice b;
	ExternalDevice(InternalDevice b){
		this.b = b;
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(500 + (int) (Math.random()*3000));
			}
			catch(Exception e){
				
			}
			if(flag) {
				b.sendPacket(1 + (int) (Math.random()*9),this.getName());
			}
				
		}
	}
}
public class TokenBucketAlgorithmUser {
	int n;
	int k;
	public TokenBucketAlgorithmUser(int token,int devices) {
		    n = token;
		    k = devices;
			
	}
	public void runThreads(InternalDevice b) {
		
		Thread tokens = new TokenAdder(b);
		try{
			tokens.start();
    		for(int i = 1; i <= k ;i++)
    		{
    		    String str = "Source " + i;
    		    Thread packets = new ExternalDevice(b);
    		    packets.setName(str);
    		    packets.start();   
    		}
		}
		catch(Exception e1){}
	
		
	}
	   
}
	
