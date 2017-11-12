package pl.lodz.p.it.spjava.medcenter.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object additionalInvokeForMethod(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder("Method called "
                + invocation.getTarget().getClass().getName() + "."
                + invocation.getMethod().getName());
        sb.append(" with identity: " + sessionContext.getCallerPrincipal().getName());
        try {
            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" with parameter " + param.getClass().getName() + "=" + param.toString());
                    } else {
                        sb.append(" with parameter null");
                    }
                }
            }
            long startTime = System.currentTimeMillis();
            Object result = invocation.proceed();
            long duration = System.currentTimeMillis() - startTime;
            sb.append(" invoking time " + duration + " ms");

            if (result != null) {
                sb.append(" returns " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" returns null");
            }
            return result;
        } catch (Exception ex) {
            sb.append(" exception " + ex);
            throw ex;
        } finally {
            Logger.getGlobal().log(Level.INFO, sb.toString());
        }
    }
}
