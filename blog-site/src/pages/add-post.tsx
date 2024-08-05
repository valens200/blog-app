import { ArrowLeft } from "lucide-react";
import { useNavigate } from "react-router-dom";

import { CreatePostForm } from "@/components/posts/CreatePostForm";
import { Button } from "@/components/ui/button";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Header } from "@/components/Header";
import { Footer } from "@/components/Footer";

export const AddPost: React.FC = () => {
  const navigate = useNavigate();

  return (
    <MainLayout>
      <main className="w-full">
        <Header />
        <div className="w-full mx-auto max-w-[856px]">
          <div className="flex justify-between items-center py-4">
            <Button
              className="flex gap-3"
              variant={`ghost`}
              onClick={() => navigate(-1)}
            >
              <ArrowLeft />
              <span className="md:inline-block hidden">Go back</span>
            </Button>
            <h2 className="text-xl font-semibold">Create a new post</h2>
            <div className="h-10 w-10 rounded-full bg-muted flex justify-center items-center md:mr-0 mr-4">
              <p className="font-semibold">PG</p>
            </div>
          </div>
          <main className="space-y-4 px-4">
            <CreatePostForm />
          </main>
        </div>
        <div className="flex flex-col justify-end bg-black fixed bottom-0 w-full">
          <Footer />
        </div>
      </main>
    </MainLayout>
  );
};
