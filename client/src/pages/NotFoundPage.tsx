import { Button } from "@/components/ui/button";
import { ArrowLeft } from "lucide-react";
import React from "react";
import { useNavigate } from "react-router-dom";

const NotFoundPage: React.FC = () => {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gradient-to-tr from-sky-100  ">
      <h1 className="text-9xl font-bold animate-bounce">404</h1>
      <h2 className="text-3xl mt-4">Page Not Found</h2>
      <p className="mt-2 mb-4 text-lg text-center px-4">
        Sorry, the page you are looking for does not exist.
      </p>
      <Button
        variant={`ghost`}
        className="gap-3 border p-4"
        onClick={() => navigate(-1)}
      >
        <ArrowLeft />
        Go back
      </Button>
    </div>
  );
};

export default NotFoundPage;
