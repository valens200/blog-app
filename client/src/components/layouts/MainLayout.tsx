import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import nprogress from "nprogress";

export const MainLayout: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  let location = useLocation();

  useEffect(() => {
    nprogress.start();
    nprogress.done();
  }, [location.pathname]);

  return <>{children}</>;
};
