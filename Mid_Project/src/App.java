
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import Data.EmployeeDat;
import java.util.Comparator;

public class App {
    Scanner scan = new Scanner(System.in);
    Vector<EmployeeDat> data = new Vector<>();
    int adminCount=0, superVisorCount=0, managerCount=0;
    public static void main(String[] args) throws Exception {
        new App();
    }
    void clear() {
		for(int x=0;x<30;x++) System.out.println("");
	}
    void calculateBonusWage(){
        if((superVisorCount-1)%3 == 0 && superVisorCount>1){
            int c=0;
            System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
            for(int i=0;i<data.size()-1;i++){
                if(data.get(i).getPosition().equals("Supervisor")){
                    //System.out.println(data.get(i).getName() + " akan dinaikan gajinya");
                    c++;
                    double wage;
                    wage = 6000000 + (6000000*0.075);
                    data.get(i).setWage(wage);
                    if(c == superVisorCount-1){
                        System.out.println(data.get(i).getCode()+" ");
                    }
                    else{
                        System.out.print(data.get(i).getCode()+", ");
                    }
                }
            }
        }
        else if((adminCount-1)%3 == 0 && adminCount>1){
            int c=0;
            System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
            for(int i=0;i<data.size()-1;i++){
                if(data.get(i).getPosition().equals("Admin")){
                    //System.out.println(data.get(i).getName() + " akan dinaikan gajinya");
                    c++;
                    double wage;
                    wage = 4000000 + (4000000*0.05);
                    data.get(i).setWage(wage);
                    if(c == adminCount-1){
                        System.out.println(data.get(i).getCode()+" ");
                    }
                    else{
                        System.out.print(data.get(i).getCode()+", ");
                    }
                }
            }
        }
        else if((managerCount-1)%3 == 0 && managerCount>1){
            int c = 0;
            System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
            for(int i=0;i<data.size()-1;i++){
                if(data.get(i).getPosition().equals("Manager")){
                    //System.out.println(data.get(i).getName() + " akan dinaikan gajinya");
                    c++;
                    double wage;
                    wage = 8000000 + (8000000*0.1);
                    data.get(i).setWage(wage);
                    if(c == managerCount-1){
                        System.out.println(data.get(i).getCode()+" ");
                    }
                    else{
                        System.out.print(data.get(i).getCode()+", ");
                    }
                }
            }
            
        }
    }
    void insertData()
    {
        String name, gender, position, code;
        double wage;

        scan.nextLine();
        System.out.print("Input nama karyawan [>= 3]: "); name = scan.nextLine();
        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
        do{
            gender = scan.nextLine();
        }while(!gender.equals("Laki-laki")&& !gender.equals("Perempuan"));

        code = codeGenerator();
        do{
            System.out.print("Input position: "); position = scan.nextLine();
        }while(!position.equals("Manager") && !position.equals("Supervisor") && !position.equals("Admin"));
        
        if(position.equals("Manager")){
            wage = 8000000;
            data.add(new Data.EmployeeDat(name, code, gender, position, wage));
            managerCount++;
        }
        else if(position.equals("Supervisor")){
            wage = 6000000;
            data.add(new Data.EmployeeDat(name, code, gender, position, wage));
            superVisorCount++;
        }
        else if(position.equals("Admin")){
            wage = 4000000;
            data.add(new Data.EmployeeDat(name, code, gender, position, wage));
            adminCount++;
        }
        System.out.println("Berhasil menambahkan karyawan dengan id " + code);
        calculateBonusWage();
    }
    void viewData()
    {
        sort();
        int count = 0;
        if(data.isEmpty())
            System.out.println("data is empty");
        else{
            //System.out.println("No     Kode karyawan      Nama Karyawan          Jenis Kelamin           Jabatan             Gaji karyawan");
            for(int i=0;i<data.size();i++){
                System.out.println((count+1) + ". " + data.get(i).getCode() + " " + data.get(i).getName() + " " + data.get(i).getGender() + " "+ data.get(i).getPosition() + " " + data.get(i).getWage() + " juta");
                count++;
            }
        }

    }
    void updateData()
    {
        viewData();
        int inp = 0;
        System.out.println("Input nomor urutan karyawan yang ingin diupdate: ");
        do{
            inp = scan.nextInt();
        }while(inp<0 || inp>data.size());
        String name1, gender1, position1, code1;
        double wage1;
        name1 = data.get(inp-1).getName();
        gender1 = data.get(inp-1).getGender();
        position1 = data.get(inp-1).getPosition();
        code1 = data.get(inp-1).getCode();
        wage1 = data.get(inp-1).getWage();

        String name, gender, position;
        double wage;
        scan.nextLine();
        System.out.print("Input nama karyawan [>=3]: "); name = scan.nextLine();
        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case sensitive): "); gender = scan.nextLine();
        do{
            System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case sensitive): "); position = scan.nextLine();
        }while(!position.equals("Manager") && !position.equals("Supervisor") && !position.equals("Admin") && !position.equals("0"));
        
        if(name.equals("0")) name = name1;
        if(gender.equals("0")) gender = gender1;

        if(position.equals("Manager")){
            if(position1.equals("Supervisor")){
                superVisorCount--;
            }
            else if(position1.equals("Manager")){
                managerCount--;
            }
            else if(position1.equals("Admin")){
                adminCount--;
            }
            managerCount++;
            wage = 8000000;
            data.set(inp-1 , new Data.EmployeeDat(name, code1, gender, position, wage));
        }
        else if(position.equals("Supervisor")){
            wage = 6000000;
            if(position1.equals("Supervisor")){
                superVisorCount--;
            }
            else if(position1.equals("Manager")){
                managerCount--;
            }
            else if(position1.equals("Admin")){
                adminCount--;
            }
            superVisorCount++;
            data.set(inp-1 , new Data.EmployeeDat(name, code1, gender, position, wage));
        }
        else if(position.equals("Admin")){
            wage = 4000000;
            if(position1.equals("Supervisor")){
                superVisorCount--;
            }
            else if(position1.equals("Manager")){
                managerCount--;
            }
            else if(position1.equals("Admin")){
                adminCount--;
            }
            adminCount++;
            data.set(inp-1 , new Data.EmployeeDat(name, code1, gender, position, wage));
        }
        else if(position.equals("0")){
            wage = wage1;
            position = position1;
            data.set(inp-1 , new Data.EmployeeDat(name, code1, gender, position, wage));
        }
        calculateBonusWage();
        System.out.println("Berhasil Mengupdate karyawan dengan id " + code1);
    }
    void deleteData()
    {
        int count = 0;
        int input = 0;
        if(data.isEmpty()){
            System.out.println("Data kosong");
        }
        else{
            for(int i=0;i<data.size();i++){
                System.out.println((count+1)+". "+ data.get(i).getCode()+" "+data.get(i).getName()+" "+data.get(i).getGender()+" "+data.get(i).getPosition()+" "+ data.get(i).getWage()+ " juta");
            }
            do{
                System.out.print("Input nomor urutan karyawan yang ingin dihapus: "); input = scan.nextInt();
                if(input == 0){
                    break;
                }
            }while(input < 0 || input > data.size());
            if(input!= 0){
                System.out.println("Karyawan dengan kode "+ data.get(input-1).getCode() +" berhasil dihapus");
                data.remove(input-1);
            }
        }
    }
    String codeGenerator(){
        Random random = new Random();

        String range = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Generate the First two Characters in ID
        char one = range.charAt(random.nextInt(range.length()));
        char two = range.charAt(random.nextInt(range.length()));

        int number = random.nextInt(9999);
        String numberStr = String.valueOf(number);

        StringBuilder sb = new StringBuilder();
        sb.append(one);
        sb.append(two);
        sb.append('-');
        sb.append(numberStr);

        String code = sb.toString();
        return code;
    }
    void sort() {
		data.sort(new Comparator<>() {
			public int compare(EmployeeDat a, EmployeeDat b) {
				return a.getName().compareTo(b.getName());
			}
			
		});
	}
    public App()
    {
        int input = 0;
        do{
            clear();
            System.out.println("1. insert data karyawan");
            System.out.println("2. view data karyawan");
            System.out.println("3. update data karyawan");
            System.out.println("4. delete data karyawan");
            System.out.println("5. exit");
            System.out.print(">> ");
            input = scan.nextInt();

            if(input == 1){
                clear();
                insertData();
                System.out.println("ENTER to continue"); scan.nextLine(); 
            }
            else if(input == 2){
                clear();
                viewData();
                System.out.println("ENTER to continue"); scan.nextLine(); scan.nextLine();
            }
            else if(input == 3){
                clear();
                updateData();
                System.out.println("ENTER to continue"); scan.nextLine(); 
            }
            else if(input == 4){
                clear();
                deleteData();
                System.out.println("ENTER to continue"); scan.nextLine(); scan.nextLine();
            }
            
        }while(input!= 5);
    }
}
