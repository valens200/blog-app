import { ChevronLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

import { HeaderController } from "@/components/HeaderController";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Button } from "@/components/ui/button";
import { RegisterForm } from "@/components/auth/RegisterForm";

export const Register: React.FC = () => {
  const navigate = useNavigate();

  return (
    <MainLayout>
      <HeaderController title="Sign up for free" />
      <div className="grid w-full md:grid-cols-2 grid-cols-1 h-full">
        <div className="flex justify-center items-center sm:p-0 p-4 bg-gradient-to-br from-sky-100 via-purple-200 to-yellow-50">
          <div className="flex flex-col gap-3 items-center bg-secondary w-96 rounded-md shadow-md px-4 py-6">
            <img src="/logo-lg.svg" alt="/Blog logo" />
            <p className="text-lg">Create an account</p>
            <p className="text-sm text-center">
              By logging in or signing up using the options above, you agree to
              /Blog's Terms & Conditions and Privacy Policy
            </p>
            <RegisterForm />
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
              &quot;It's amazing to see how fast bloggers go from 0 to Blog
              under a domain they own on /Blog 🤯. It reminds me a lot of what
              Google did for search&quot;
            </p>
            <div className="flex gap-3 md:p-0 p-4">
              <img
                src="https://cdn.hashnode.com/res/hashnode/image/upload/v1711971592110/1615ae9d-e30b-473c-a789-dc342b23d02d.png?auto=compress"
                className="h-12 w-12 rounded-full"
              />
              <div>
                <p>Guillermo Rauch</p>
                <p>CEO, Vercel</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};
