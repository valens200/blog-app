import { CommentSection } from "@/components/comments/CommentSection";
import { Header } from "@/components/Header";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Button } from "@/components/ui/button";
import { authApi } from "@/utils/api/constants";
import { getErrorFromResponse } from "@/utils/functions/function";
import { format } from "date-fns";
import { ArrowLeft } from "lucide-react";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useSWR from "swr";

export const PostDetail: React.FC = () => {
  const { id } = useParams();
  const [data, setData]: any = useState();
  const navigate = useNavigate();

  useSWR(`/posts/${id}`, async (url) => {
    try {
      const response = await authApi.get(url);
      setData(response.data.data);
      console.log(data);
    } catch (error) {
      getErrorFromResponse(error);
    }
  });

  if (!id) {
    navigate(-1);
  }

  return (
    <MainLayout>
      <main className="w-full">
        <Header />
        <div className="md:px-0 px-4 max-w-[856px] py-8 mx-auto space-y-5">
          <Button
            variant={`ghost`}
            className="gap-3"
            onClick={() => navigate(-1)}
          >
            <ArrowLeft />
            Go back
          </Button>
          {data != null ? (
            <>
              <h2 className="text-3xl text-left text-primary mt-4">
                {data.title}
              </h2>
              <p className="text-left text-muted-foreground">
                Learn how we&apos;re experimenting with open source AI models to
                systematically incorporate customer feedback to supercharge our
                product roadmaps.
              </p>
              <img
                src="https://github.blog/wp-content/uploads/2024/07/AI-DarkMode-2-1.png?w=1200"
                alt="Image"
                className="rounded-xl w-full"
              />
              <div>
                <p>{`By ${data.author.email}`}</p>
                <p className="text-muted-foreground">
                  {format(new Date(data.dateTime), "yyyy MMMM ddd")}
                </p>
              </div>
              <article className="prose lg:prose-xl">
                <p>{data.content}</p>
              </article>
              <CommentSection comments={data.comments} postId={id!} />
            </>
          ) : (
            <div>
              <p>Loading....</p>
            </div>
          )}
        </div>
      </main>
    </MainLayout>
  );
};
