import { ArrowLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

import { EditPostForm } from "@/components/posts/EditPostForm";
import { Button } from "@/components/ui/button";
import { authentiCate } from "@/utils/functions/function";
import { useEffect } from "react";

export const EditPost: React.FC = () => {
  const navigate = useNavigate();
  useEffect(() => {
    if (!authentiCate()) {
      navigate("/");
    }
  }, []);

  return (
    <div className="w-full mx-auto max-w-[856px]">
      <div className="flex justify-between items-center py-4">
        <Button
          className="flex gap-3"
          variant={`ghost`}
          onClick={() => navigate("/")}
        >
          <ArrowLeft />
          <span className="md:inline-block hidden">Go back</span>
        </Button>
        <h2 className="text-xl font-semibold">Edit post</h2>
        <div className="h-10 w-10 rounded-full bg-muted flex justify-center items-center md:mr-0 mr-4">
          <p className="font-semibold">PG</p>
        </div>
      </div>
      <main className="space-y-4 px-4">
        <EditPostForm />
      </main>
    </div>
  );
};
