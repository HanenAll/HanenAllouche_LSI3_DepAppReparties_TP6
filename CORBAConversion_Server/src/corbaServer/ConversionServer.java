package corbaServer;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import service.ConversionImpl;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            /* Initialiser le middleware CORBA avec des paramètres passés via args : le num de port et l’@IP
            de l’annuaire, pour notre cas on va utilisé JNDI on a pas besoin de préciser ces arguments. */
            ORB orb = ORB.init(args, null);
            //dans un serveur corba il faut activer le PAOManager
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); // type de retour de mon
            // objet va etre convertit de type objet à un reference
            //l’activation ce fait via la méthode activate
            poa.the_POAManager().activate();

            //creation d'une instance de l'implementation du service de conversion
            ConversionImpl od= new ConversionImpl();

            // pour utiliser JNDI il faut aussi créer un objet de InitialContext
            Context ctx= new InitialContext();

            //publication de l'objet de conversion dans l'annuaire JNDK avec le nom "OD"
            ctx.rebind("OD",poa.servant_to_reference(od));

            //Execution de l'ORB , ce qui mainteindra le serveur en attente des demandes des clients
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
