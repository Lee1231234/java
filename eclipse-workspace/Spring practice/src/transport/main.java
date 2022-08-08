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
		    	 status = "������";
		    	 count = 0;
		    	 nums++;
		    	 System.out.println("�� ��ȣ :"+car_number);
		    	 
		    }



			@Override
			int speed_change(int change_speed) {
				// TODO Auto-generated method stub
				if(fuel_volume<10) {
					System.out.println("���: �������� Ȯ���� �ּ���");
				}
				speed+=change_speed;
				return 0;
			}

			@Override
			String status_change(String change_status) {
				if(fuel_volume<0) {
					status = "��������";
					return null;
				}
				
				// TODO Auto-generated method stub
				status = change_status;
				if(fuel_volume<10) {
					System.out.println("���� :"+status);
					System.out.println("��� :���� �ʿ�");
				}
				return null;
			}

			
			void ride_customer(int R_customer) {
				// TODO Auto-generated method stub
				if(max_customer-R_customer<0) {
					System.out.println("��� : �ִ� �°� �� �ʰ� ");
					return ;
				}
				if(status.equals("������")) {
					count = R_customer;
					current_customer = R_customer;
					System.out.println("ž�½°��� ="+current_customer);
					System.out.println("�ܿ��°��� ="+(max_customer-current_customer));
					System.out.println("�� ��� Ȯ�� ="+(count*fee));
				}
				
			}



			@Override
			void fuel_change(int fuel) {
				// TODO Auto-generated method stub
				fuel_volume = fuel_volume+fuel;
				if(status.equals("��������")) {
					System.out.println("���� ="+status);
				}
				System.out.println("������ ="+fuel_volume);
				if(fuel_volume<10) {
					
					status_change("��������");
					
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
			status = "�Ϲ�";
			max_customer=4;
			distance_fee = 1000;
			fee = 3000;
			distance = 1;
			
			System.out.println("�ýù�ȣ : "+car_number);
			System.out.println("������ : "+fuel_volume);
			System.out.println("���� : "+status);
			
			tnums++;
		}
		void start_drive() {
			if(fuel_volume<10) {
				System.out.println("����Ұ�");
				return;
			}
			status="�Ϲ�";
		}
		@Override
		int speed_change(int change_speed) {
			// TODO Auto-generated method stub
			if(fuel_volume<10) {
				System.out.println("���: �������� Ȯ���� �ּ���");
			}
			speed+=change_speed;
			return 0;
		}

		@Override
		String status_change(String change_status) {
			// TODO Auto-generated method stub
			status=change_status;
			if(status.equals("����Ұ�")) {
				System.out.println("��� : "+status);
			}
			return null;
		}

		
		void ride_customer(int R_customer,String name,int distance ) {
			if(status.equals("�Ϲ�")) {
				if((max_customer-R_customer)<0) {
					System.out.println("��� = �ִ� �°� �� �ʰ�");
					return;
				}
				status_change("���� ��");
				current_customer = R_customer;
				dir= name;
				current_fee = fee+((distance-1)*distance_fee);
				total_fee += current_fee;
				System.out.println("ž�� �°� �� = "+current_customer);
				System.out.println("�ܿ� �°� �� = "+(max_customer-current_customer));
				System.out.println("�⺻ ��� Ȯ�� = "+fee);
				System.out.println("������ = "+dir);
				System.out.println("���������� �Ÿ� = "+distance+"km");
				System.out.println("������ ��� = "+current_fee);
				
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		void fuel_change(int fuel) {
			fuel_volume+=fuel;
			// TODO Auto-generated method stub
			
		}
		void fee_claim() {
			System.out.println("������ : "+fuel_volume);
			if(fuel_volume<10) {
				status_change("����Ұ�");
				
			}else {
				status_change("�Ϲ�");
			}
			System.out.println("������� : "+total_fee);
			current_customer =0;
			// TODO Auto-generated method stub
			if(fuel_volume<10) {
				System.out.println("��� :���� �ʿ�");
			}
			
		}
		
	}
	public static void main(String args[]) throws java.io.IOException {
		bus bus1 = new bus();
		bus bus2 = new bus();

		bus1.ride_customer(2);
		bus1.fuel_change(-50);
		bus1.status_change("��������");
	
		bus1.fuel_change(10);
		bus1.status_change("������");
		bus1.ride_customer(45);
		bus1.ride_customer(5);
		bus1.fuel_change(-55);
		System.out.println(" ");
		texi texi1 = new texi();
		texi texi2 = new texi();
		texi1.ride_customer(2,"���￪",2);
		texi1.fuel_change(-80);
		texi1.fee_claim();
		texi1.ride_customer(5,"",2);
		texi1.ride_customer(3,"���ε����д�����",12);
		texi1.fuel_change(-20);
		texi1.fee_claim();
	}
		
}
