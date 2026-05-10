package pe.edu.upc.tpbackinkametrics.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pe.edu.upc.tpbackinkametrics.securities.JwtUserDetails;

public class SecurityUtils {
    public static int getIdEmpresaActual() {
        // Obtenemos la autenticación actual de Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof JwtUserDetails) {
            // Hacemos el cast a tu clase custom para sacar el int
            return ((JwtUserDetails) auth.getPrincipal()).getIdEmpresa();
        }
        return 0; // Si no hay usuario o empresa, devolvemos 0
    }
}
