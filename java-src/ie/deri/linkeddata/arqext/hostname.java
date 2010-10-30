package ie.deri.linkeddata.arqext;

import java.net.URI;
import java.net.URISyntaxException;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.sparql.expr.ExprEvalException;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.function.FunctionBase1;
import com.hp.hpl.jena.sparql.util.FmtUtils;

public class hostname extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Node n = v.asNode();
		try {
			if (!n.isURI()) throw new ExprEvalException("Not a URI: " + FmtUtils.stringForNode(n));
			String host = new URI(n.getURI()).getHost();
			if (host == null) {
				throw new ExprEvalException("Host is null: " + FmtUtils.stringForNode(n));
			}
			return NodeValue.makeString(host);
		} catch (URISyntaxException ex) {
			throw new ExprEvalException(ex);
		}
	}
}
