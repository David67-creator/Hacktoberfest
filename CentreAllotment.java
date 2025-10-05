import java.util.ArrayList;
import java.util.*;
public class CentreAllotment {
    String name;
    String fname;
    long mobile;
    String address;
    String gender;
    long regNo;
    String allotedCentre = null;
    final long demo = 102025001;
    static int total=0;
    String choice[]=new String[3];
    final static String centreList[] = {"Indore","bhopal","ujjain","dewas","dhar","khandwa","ratlam"};
    final static int centreCapacity[] = {20,11,200,15,14,23,0};
    static int allotedList[] = {0,0,0,0,0,0,0};
    public void registration(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name : ");
        name = sc.nextLine();
        System.out.print("Enter your Father name : ");
        fname = sc.nextLine();
        System.out.print("Enter your mobile no : ");
        mobile = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter your address :");
        address = sc.nextLine();
        System.out.print("Enter your gender : ");
        gender = sc.nextLine();
        System.out.println("Basic details have saved successfully please go to fill your choices : ");
        
    }
    public void allotChoice(){
        outer : for(int i=0;i<3;i++){
                    for(int j=0;j<centreList.length;j++){
                        if(choice[i].equalsIgnoreCase(centreList[j])){
                            if(allotedList[j]<centreCapacity[j]){
                                allotedCentre = centreList[j];
                                allotedList[j]++;
                                break outer;
                            }
                            else{
                                continue outer;
                            }
                        }
                    }
                }
            }
    public void centreChoice(){
       Scanner sc = new Scanner(System.in);
       for(int i=0;i<3;i++){
        System.out.print("Enter your choice "+i+" : ");
        choice[i] = sc.nextLine();
       }
       total++;
       regNo = demo+total;
       allotChoice();
       System.out.println("registraion successful your reg No. : "+regNo);

    }
    public void showCandidate(){
        String details = "name\t : "+name+"\nfname\t : "+fname+"\nMobile\t :"+mobile+" "+"\nchoice\t : "+choice[0]+" "+choice[1]+" "+choice[2];
        details = details + "\nAlloted centre\t : "+allotedCentre;
        System.out.println(details);
    }
    public static void showCentreDetails(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter centre name : ");
        String centre = sc.nextLine();
        boolean check = true;
        for(int i=0;i<centreList.length;i++){
            if(centreList[i].equalsIgnoreCase(centre)){
                System.out.println("Alloted seats :\t"+allotedList[i]);
                int free = centreCapacity[i]-allotedList[i];
                System.out.println("Free seats :\t"+free);
                System.out.println("Total seats :\t"+centreCapacity[i]);
                check=false;
                break;
            }
        }
        if(check){
            System.out.println("Entered centre is not a valid centre : ");
        }
    }
    public static void showAllCentreDetails(){
        int free;
        System.out.println("________________________________");
        System.out.println("|Centre\t|alloted|free\t|total\t|");
        System.out.println("|_______|_______|_______|_______|");
        for(int i=0;i<centreCapacity.length;i++){
            free = centreCapacity[i] - allotedList[i];
            System.out.println(centreList[i]+"\t|"+allotedList[i]+"\t|"+free+"\t|"+centreCapacity[i]+"\t|");
        }
    }
    public static void main(String s[]){
        Scanner sc = new Scanner(System.in);
        ArrayList<CentreAllotment> candidates = new ArrayList<>();
        while(true){
            int choice;
            System.out.println("1 for Candidate Registration\n2 for Enter choice for centre\n3 for show candidate\n4 for show center details\n5 for show all centers\n6 for exit");
            System.out.print("Enter your choice :");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    
                    candidates.add(new CentreAllotment());
                    candidates.get(candidates.size()-1).registration(); 
                    break;
                case 2:
                    boolean find=false;
                    System.out.print("Enter your mobile no : ");
                    long temp=sc.nextLong();
                    for(int i=0;i<candidates.size();i++){
                        if(candidates.get(i).mobile == temp){
                            candidates.get(i).centreChoice();
                            find=true;
                            break;
                        }
                    }
                    if(find == false){
                        System.out.println("You are not register first register please : ");
                    }
                    break;
                case 3:
                    System.out.print("Enter your registration no : ");
                    long reg = sc.nextLong();
                    int i=0;
                    for(i=0;i<candidates.size();i++){
                        if(candidates.get(i).regNo == reg){
                            candidates.get(i).showCandidate();
                            break;
                        }
                    }
                    if(i>=candidates.size()){
                        System.out.println("No candidate found : ");
                    }
                    break;
                case 4:
                    CentreAllotment.showCentreDetails();
                    break;
                case 5:
                    CentreAllotment.showAllCentreDetails();
                    break;

                case 6:
                    System.exit(0);
                    break;

            }
            

        }
    }
}
