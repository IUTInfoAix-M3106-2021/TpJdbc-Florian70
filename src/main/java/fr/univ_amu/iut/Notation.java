package fr.univ_amu.iut;

public class Notation {
    private int moyCC;
    private int moyTest;

    public Notation(int moyCC, int moyTest) {
        this.moyCC = moyCC;
        this.moyTest = moyTest;
    }

    public Notation() {
    }

    public float getMoyCC() {
        return moyCC;
    }

    public void setMoyCC(int moyCC) {
        this.moyCC = moyCC;
    }

    public float getMoyTest() {
        return moyTest;
    }

    public void setMoyTest(int moyTest) {
        this.moyTest = moyTest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(moyCC);
        result = prime * result + Float.floatToIntBits(moyTest);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Notation other = (Notation) obj;
        if (Float.floatToIntBits(moyCC) != Float.floatToIntBits(other.moyCC))
            return false;
        if (Float.floatToIntBits(moyTest) != Float.floatToIntBits(other.moyTest))
            return false;
        return true;
    }
}
