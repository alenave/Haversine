package utils;

import java.util.Map;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.InputStream;

public class Request {
	public void post(Map<String, Marker> allNews, String endPoint) {

		InputStream input = null;
		try {
			input = Request.class.getResourceAsStream("/service_account.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(input)).setDatabaseUrl(Config.FIREBASE_URL).build();
		if (!(FirebaseApp.getApps().size() > 0)) {
			FirebaseApp.initializeApp(options);
		}

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference dbref = database.getReference("list");
		DatabaseReference usersRef = dbref.child(endPoint);
		usersRef.setValue(allNews);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (FirebaseDatabase.getInstance() != null) {
			System.out.println("i m done " + endPoint);
			Firebase.goOffline();
		}
	}
}
