package just.monika.开裂我端子的反编译我几行代码死几个妈.rose.utils.altening;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public final class SSLController {

    static final Logger log = LogManager.getLogger();

    @NonNull
    private final SSLSocketFactory allTrustingFactory;
    @NonNull
    private final SSLSocketFactory originalFactory;
    @NonNull
    private final HostnameVerifier originalHostVerifier;

    public SSLController() {
    	@NonNull 
    	SSLContext context = null;
        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, ALL_TRUSTING_TRUST_MANAGER, new SecureRandom());
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.allTrustingFactory = context.getSocketFactory();
        this.originalFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        this.originalHostVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    }

    public void enableCertificateValidation() {
        updateCertificateValidation(this.originalFactory, this.originalHostVerifier);
    }

    public void disableCertificateValidation() {
        updateCertificateValidation(this.allTrustingFactory, ALTENING_HOSTING_VERIFIER);
    }

    private void updateCertificateValidation(@NonNull SSLSocketFactory factory, @NonNull HostnameVerifier hostnameVerifier) {
        HttpsURLConnection.setDefaultSSLSocketFactory(factory);
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
    }

    private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER;
    private static final HostnameVerifier ALTENING_HOSTING_VERIFIER;

    static {
        ALL_TRUSTING_TRUST_MANAGER = new TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        ALTENING_HOSTING_VERIFIER = (hostname, session) -> hostname.equals("authserver.thealtening.com") || hostname.equals("sessionserver.thealtening.com");
    }

}
