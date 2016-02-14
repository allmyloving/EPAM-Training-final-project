package ua.nure.serdyuk.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Message;

/**
 * This filter is used to set request encoding, so using different languages,
 * data would be correctly displayed.
 * 
 * @author Daria Serdiuk
 * @see LocaleFilter
 */
@WebFilter(filterName = "/CharsetFilter", urlPatterns = "*")
public class CharsetFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(CharsetFilter.class);

	private static final String ENCODING = "utf8";

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(ENCODING);
		LOG.info(String.format(Message.ENCODING_IS_SET, ENCODING));

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
