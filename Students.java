public class Students {
    private int Id;
    private String first_Name;
    private String last_Name;
    private String gender;
    private String  career_aspiration;
    private int math_score;

    //si esta encapsulado, es porque el atributo es privado y los metodos asociados a ese atributo son publicos


    public Students(int Id, String first_Name, String last_Name, String gender, String career_aspiration, int math_score) {  //el constructor. Crea un estudiante con todo esto
        this.Id=Id;
        this.first_Name= first_Name;
        this.last_Name = last_Name;
        this.gender = gender;
        this.career_aspiration= career_aspiration;
        this.math_score = math_score;


    }

    // Getters   (obtener informacion)

    public int getId() {
        return Id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getGender() {
        return gender;
    }

    public String getCareer_aspiration() {
        return career_aspiration;
    }

    public int getMath_score() {
        return math_score;
    }


    // Setters   (modificar informacion)

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCareer_aspiration(String career_aspiration) {
        this.career_aspiration = career_aspiration;
    }

    public void setMath_score(int math_score) {
        this.math_score = math_score;
    }


    @Override (sobreescribir)
    public String toString() {  //to string es para imprimir todos los atributos
        return "Student ID: " + Id + "\n" +
               "First Name: " + first_Name + "\n" +
               "Last Name: " + last_Name + "\n" +
               "Gender: " + gender + "\n" +
               "Career Aspiration: " + career_aspiration + "\n" +
               "Math Score: " + math_score + "\n";
    }
}
