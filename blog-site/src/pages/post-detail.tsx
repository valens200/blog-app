import { CommentSection } from "@/components/comments/CommentSection";
import { Header } from "@/components/Header";
import { MainLayout } from "@/components/layouts/MainLayout";
import { confirmModal } from "@/components/modals/ConfirmModal";
import { Button } from "@/components/ui/button";
import { authApi, images } from "@/utils/api/constants";
import { getErrorFromResponse } from "@/utils/functions/function";
import { format } from "date-fns";
import { ArrowLeft, Delete, Edit } from "lucide-react";
import { useEffect, useState } from "react";
import toast from "react-hot-toast";
import { useNavigate, useParams } from "react-router-dom";
import useSWR from "swr";

export const PostDetail: React.FC = () => {
  const { id } = useParams();
  const [data, setData]: any = useState();
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  useSWR(`/posts/${id}`, async (url) => {
    try {
      const response = await authApi.get(url);
      setData(response.data.data);
      console.log(data);
    } catch (error) {
      getErrorFromResponse(error);
      navigate(-1);
    }
  });

  if (!id) {
    navigate(-1);
  }
  const handleEdit = () => {
    localStorage.setItem("post", JSON.stringify(data));
    navigate("/admin/new-post");
  };

  const isOwner = true;
  return (
    <MainLayout>
      <main className="w-full">
        <Header />
        <div className="md:px-0 px-4 max-w-[856px] py-8 mx-auto space-y-5">
          <div className="flex justify-between">
            <Button
              variant={`ghost`}
              className="gap-3"
              onClick={() => navigate(-1)}
            >
              <ArrowLeft />
              Go back
            </Button>
            {isOwner && (
              <div className="flex gap-4">
                <Button onClick={handleEdit} className="gap-3">
                  <Edit className="h-4 w-4" />
                  Edit
                </Button>
                <Button
                  variant={"destructive"}
                  className="gap-3"
                  onClick={() => {
                    confirmModal(
                      "Are you sure you want to delete this post?",
                      async () => {
                        try {
                          setLoading(true);
                          await authApi.delete(`/posts/${id}`);
                          navigate(-1);
                          toast.success("The post was delted successfully");
                        } catch (error) {
                          toast.error(getErrorFromResponse(error));
                        } finally {
                          setLoading(false);
                        }
                        // callback function executed when the user confirms actions
                      }
                    );
                  }}
                >
                  <Delete className="h-5 w-5" />
                  Delete
                </Button>
              </div>
            )}
          </div>

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
                src={images.image1}
                alt="Image"
                className="rounded-xl w-full"
              />
              <div>
                <p>{`By ${
                  data.author != null ? data.author.email : "No author"
                }`}</p>
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
