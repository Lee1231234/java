package transport;
import java.awt.font.TextHitInfo;
import java.io.*;
import java.util.*;


abstract class transport {
	protected int car_number;
	protected int fuel_volume;
	protected int fee;
	protected int speed;
	protected int max_customer;
	protected String status;
	
	transport(){
		speed=0;
		fuel_volume=100;
		
	}

	abstract int speed_change(int change_speed);
	abstract String status_change(String change_status);
	
	abstract void fuel_change(int fuel);
	
	
}
  
	
public class main {
	 static int nums = 0;
	 static int tnums =0;
	 static class bus extends transport{
		    private int current_customer;
		    int count;
		    bus(){
		    	 car_number = nums;
		    	 max_customer = 30;
		    	 fee = 1000;
		    	 status = "운행중";
		    	 count = 0;
		    	 nums++;
		    	 System.out.println("차 번호 :"+car_number);
		    	 
		    }



			@Override
			int speed_change(int change_speed) {
				// TODO Auto-generated method stub
				if(fuel_volume<10) {
					System.out.println("경고: 주유량을 확인해 주세요");
				}
				speed+=change_speed;
				return 0;
			}

			@Override
			String status_change(String change_status) {
				if(fuel_volume<0) {
					status = "차고지행";
					return null;
				}
				
				// TODO Auto-generated method stub
				status = change_status;
				if(fuel_volume<10) {
					System.out.println("상태 :"+status);
					System.out.println("경고 :주유 필요");
				}
				return null;
			}

			
			void ride_customer(int R_customer) {
				// TODO Auto-generated method stub
				if(max_customer-R_customer<0) {
					System.out.println("경고 : 최대 승객 수 초과 ");
					return ;
				}
				if(status.equals("운행중")) {
					count = R_customer;
					current_customer = R_customer;
					System.out.println("탑승승객수 ="+current_customer);
					System.out.println("잔여승객수 ="+(max_customer-current_customer));
					System.out.println("총 요금 확인 ="+(count*fee));
				}
				
			}



			@Override
			void fuel_change(int fuel) {
				// TODO Auto-generated method stub
				fuel_volume = fuel_volume+fuel;
				if(status.equals("차고지행")) {
					System.out.println("상태 ="+status);
				}
				System.out.println("주유량 ="+fuel_volume);
				if(fuel_volume<10) {
					
					status_change("차고지행");
					
				}
			}
			
		}
	
	static class texi extends transport{
		String dir;
		int distance;
		int distance_fee,current_customer;
		int total_fee,current_fee;
		texi() {
			car_number = tnums;
			status = "일반";
			max_customer=4;
			distance_fee = 1000;
			fee = 3000;
			distance = 1;
			
			System.out.println("택시번호 : "+car_number);
			System.out.println("주유량 : "+fuel_volume);
			System.out.println("상태 : "+status);
			
			tnums++;
		}
		void start_drive() {
			if(fuel_volume<10) {
				System.out.println("운행불가");
				return;
			}
			status="일반";
		}
		@Override
		int speed_change(int change_speed) {
			// TODO Auto-generated method stub
			if(fuel_volume<10) {
				System.out.println("경고: 주유량을 확인해 주세요");
			}
			speed+=change_speed;
			return 0;
		}

		@Override
		String status_change(String change_status) {
			// TODO Auto-generated method stub
			status=change_status;
			if(status.equals("운행불가")) {
				System.out.println("상대 : "+status);
			}
			return null;
		}

		
		void ride_customer(int R_customer,String name,int distance ) {
			if(status.equals("일반")) {
				if((max_customer-R_customer)<0) {
					System.out.println("경고 = 최대 승객 수 초과");
					return;
				}
				status_change("운행 중");
				current_customer = R_customer;
				dir= name;
				current_fee = fee+((distance-1)*distance_fee);
				total_fee += current_fee;
				System.out.println("탑승 승객 수 = "+current_customer);
				System.out.println("잔여 승객 수 = "+(max_customer-current_customer));
				System.out.println("기본 요금 확인 = "+fee);
				System.out.println("목적지 = "+dir);
				System.out.println("목적지까지 거리 = "+distance+"km");
				System.out.println("지불할 요금 = "+current_fee);
				
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		void fuel_change(int fuel) {
			fuel_volume+=fuel;
			// TODO Auto-generated method stub
			
		}
		void fee_claim() {
			System.out.println("주유량 : "+fuel_volume);
			if(fuel_volume<10) {
				status_change("운행불가");
				
			}else {
				status_change("일반");
			}
			System.out.println("누적요금 : "+total_fee);
			current_customer =0;
			// TODO Auto-generated method stub
			if(fuel_volume<10) {
				System.out.println("경고 :주유 필요");
			}
			
		}
		
	}
	public static void main(String args[]) throws java.io.IOException {
		bus bus1 = new bus();
		bus bus2 = new bus();

		bus1.ride_customer(2);
		bus1.fuel_change(-50);
		bus1.status_change("차고지행");
	
		bus1.fuel_change(10);
		bus1.status_change("운행중");
		bus1.ride_customer(45);
		bus1.ride_customer(5);
		bus1.fuel_change(-55);
		System.out.println(" ");
		texi texi1 = new texi();
		texi texi2 = new texi();
		texi1.ride_customer(2,"서울역",2);
		texi1.fuel_change(-80);
		texi1.fee_claim();
		texi1.ride_customer(5,"",2);
		texi1.ride_customer(3,"구로디지털단지역",12);
		texi1.fuel_change(-20);
		texi1.fee_claim();
	}
		
}
