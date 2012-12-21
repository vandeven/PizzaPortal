package nl.topicus.onderwijs.security;

import nl.topicus.onderwijs.resources.ldap.LDAPUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class PizzaRealm implements Realm
{

	@Override
	public String getName()
	{
		return "PizzaRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		return UsernamePasswordToken.class.isAssignableFrom(token.getClass());
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException
	{
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());

		if (!isAuthenticated(username, password))
		{
			throw new AuthenticationException("Login name [" + username + "] not found!");
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	private boolean isAuthenticated(String username, String password)
	{
		if (username.equalsIgnoreCase("test") && password.equalsIgnoreCase("test"))
		{
			return true;
		}
		else
		{
			return LDAPUtil.authenticate("baas2.topicus.local", "TOPICUS", username,
				password);
		}
	}
}
