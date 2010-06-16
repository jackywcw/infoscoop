package org.infoscoop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.server.OAuthServlet;

import org.infoscoop.dao.OAuthTokenDAO;
import org.infoscoop.request.OAuthAuthenticator;
import org.infoscoop.util.SpringUtil;

public class OAuthCallbackServlet extends HttpServlet {

	public static final String PATH = "/OAuth/Callback";

	protected final Logger log = Logger.getLogger(getClass().getName());

	/**
	 * Exchange an OAuth request token for an access token, and store the latter
	 * in cookies.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		OAuthConsumer consumer = null;
		try {
			HttpSession session = request.getSession();
			String uid = (String) session.getAttribute("Uid");
			String consumerName = request.getParameter("consumer");
			String gadgetType = request.getParameter("__GADGET_TYPE__");
			
			OAuthAuthenticator authenticator = (OAuthAuthenticator)SpringUtil.getBean("oauthAuthenticator");
			final OAuthMessage requestMessage = OAuthServlet.getMessage(
					request, null);
			consumer = authenticator.getConsumer( consumerName );
			
			OAuthAccessor accessor = new OAuthAccessor(consumer);
	        accessor.accessToken = (String) session.getAttribute(consumerName+ ".accesstoken");
	        accessor.requestToken = (String) session.getAttribute(consumerName+ ".requesttoken");
	        accessor.tokenSecret = (String) session.getAttribute(consumerName+ ".tokensecret");
	        
			final String expectedToken = accessor.requestToken;
			String requestToken = request.getParameter(OAuth.OAUTH_TOKEN);
			if (requestToken == null || requestToken.length() <= 0) {
				log.warning(request.getMethod() + " "
						+ OAuthServlet.getRequestURL(request));
				requestToken = expectedToken;
				if (requestToken == null) {
					OAuthProblemException problem = new OAuthProblemException(OAuth.Problems.PARAMETER_ABSENT);
					problem.setParameter(OAuth.Problems.OAUTH_PARAMETERS_ABSENT, OAuth.OAUTH_TOKEN);
					throw problem;
				}
			} else if (!requestToken.equals(expectedToken)) {
				OAuthProblemException problem = new OAuthProblemException("token_rejected");
				problem.setParameter("oauth_rejected_token", requestToken);
				problem.setParameter("oauth_expected_token", expectedToken);
				throw problem;
			}
			List<OAuth.Parameter> parameters = null;
			String verifier = request.getParameter(OAuth.OAUTH_VERIFIER);
			if (verifier != null) {
				parameters = OAuth.newList(OAuth.OAUTH_VERIFIER, verifier);
			}
			OAuthMessage result = OAuthAuthenticator.CLIENT.getAccessToken(accessor, null, parameters);
			if (accessor.accessToken == null) {
				OAuthProblemException problem = new OAuthProblemException(OAuth.Problems.PARAMETER_ABSENT);
				problem.setParameter(OAuth.Problems.OAUTH_PARAMETERS_ABSENT, OAuth.OAUTH_TOKEN);
				problem.getParameters().putAll(result.getDump());
				throw problem;
			}
			
			// add to both db and session for high performance.
			OAuthTokenDAO.newInstance().saveAccessToken(uid, gadgetType,
					consumerName, accessor.accessToken, accessor.tokenSecret);
			session.setAttribute(consumerName + ".accesstoken",
					accessor.accessToken);
			session.setAttribute(consumerName + ".tokensecret",
					accessor.tokenSecret);
			
			PrintWriter out = response.getWriter();
			out.println("got accesstoken");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;

}
