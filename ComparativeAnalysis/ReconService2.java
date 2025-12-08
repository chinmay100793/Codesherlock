public class ReconService2 {

    // Intentionally abbreviated names – perfect for testing naming suggestions
    public void performRecon(double balA, double balB) {
        double diff = Math.abs(balA - balB);
        if (diff > 0.01) {
            logReconError(balA, balB, diff);
        }
    }

    private void logReconError(double balA, double balB, double diff) {
        System.out.println("Recon failed: balA=" + balA + ", balB=" + balB + ", diff=" + diff);
    }

    public void runDailyRecon() {
        System.out.println("Starting daily recon process...");
        // dummy logic
    }
}