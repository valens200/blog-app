import { ChevronLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

import { HeaderController } from "@/components/HeaderController";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Button } from "@/components/ui/button";
import { LoginForm } from "@/components/auth/LoginForm";

export const Login: React.FC = () => {
  const navigate = useNavigate();

  return (
    <MainLayout>
      <HeaderController title="Sign in" />
      <div className="grid w-full md:grid-cols-2 grid-cols-1 h-full">
        <div className="flex justify-center items-center sm:p-0 p-4 bg-gradient-to-br from-sky-100 via-purple-200 to-yellow-50">
          <div className="flex flex-col gap-3 items-center bg-secondary w-96 rounded-md shadow-md px-4 py-6">
            <img src="/logo-lg.svg" alt="/Blog logo" />
            <p className="text-lg">Log in</p>
            <p className="text-sm text-center">
              By logging in or signing up using the options above, you agree to
              /Blog's Terms & Conditions and Privacy Policy
            </p>
            <LoginForm />
          </div>
        </div>
        <div className="flex flex-col w-full h-full">
          <div className="flex md:justify-end justify-center w-full px-7 py-3">
            <Button
              variant={`ghost`}
              className="gap-3 text-base"
              onClick={() => navigate(-1)}
            >
              <ChevronLeft />
              Go back
            </Button>
          </div>
          <div className="flex flex-col gap-4 flex-1 mx-auto px-12 justify-center items-center">
            <p className="text-secondary-foreground text-xl">
              &quot;It's only when you start using /Blog you realize how much
              potential it has and where it can get your customers and
              business&quot;
            </p>
            <div className="flex gap-3 md:p-0 p-4">
              <img
                src="https://rdb.rw/wp-content/uploads/2023/04/Nelly.jpg"
                className="h-12 w-12 rounded-full"
              />
              <div>
                <p>Nelly</p>
                <p>RDB, Senior Advisor</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};
