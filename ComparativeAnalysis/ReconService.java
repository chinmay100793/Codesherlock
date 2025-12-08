public class ReconService {
    public void performRecon(double balanceA, double balanceB) {
        if (balanceA != balanceB) {
            logReconError(balanceA, balanceB);
        }
    }

    private void logReconError(double balA, double balB) {
        System.out.println("Recon mismatch: " + balA + " vs " + balB);
    }
}