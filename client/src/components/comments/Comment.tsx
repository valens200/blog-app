import { useMemo, useState } from "react";
import { Edit, Trash } from "lucide-react";
import { confirmModal } from "../modals/ConfirmModal";
import { useNavigate } from "react-router-dom";
import { authApi } from "@/utils/api/constants";
import toast from "react-hot-toast";
import { getErrorFromResponse } from "@/utils/functions/function";
import { EditCommentModal } from "../modals/EditModal";

interface CommentProps {
  id: any;
  content: any;
  author: any;
  createdAt: any;
}

export const Comment: React.FC<CommentProps> = ({
  id,
  content,
  author,
  createdAt,
}) => {
  const [showEditModal, setShowEditModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const handleShowEditModal = () => {
    setShowEditModal(!showEditModal);
  };
  const user = JSON.parse(localStorage.getItem("user")!);
  const isCreator = user.email == (author == null ? "" : author.email);

  return (
    <div className="items-center flex">
      <div className="flex gap-4 w-[80%]">
        <div className="flex justify-center items-center bg-muted text-muted-foreground rounded-full h-12 w-12">
          <h1 className="uppercase">
            {author.userName.charAt(0) + author.userName.charAt(2)}
          </h1>
        </div>
        <div className="flex flex-col gap-1 items-start">
          <div className="flex items-center gap-3">
            <p className="font-semibold">{author.userName} </p>
            <p className="text-sm">{createdAt}</p>

            {/* <p className="text-sm">{format(dt, "yyyy MMMM ddd")}</p> */}
          </div>
          <p className="prose">{content}</p>
        </div>
      </div>
      <div className="flex gap-x-4 w-[20%] justify-end items-center">
        {isCreator == true ? (
          <>
            <Edit
              className="h-5 w-5 text-orange-500"
              onClick={handleShowEditModal}
            />
            <Trash
              className="h-5 w-5 text-red-500"
              onClick={() => {
                confirmModal(
                  "Are you sure you want to delete this comment?",
                  async () => {
                    try {
                      setLoading(true);
                      const res = await authApi.delete(
                        `/comments/delete/${id}`
                      );
                      console.log(res);
                      toast.success("The comment was delted successfully");
                      window.location.href = window.location.href;
                    } catch (error) {
                      toast.error(getErrorFromResponse(error));
                    } finally {
                      setLoading(false);
                    }
                    // callback function executed when the user confirms actions
                  }
                );
              }}
            />
          </>
        ) : null}
      </div>
      {showEditModal && (
        <EditCommentModal
          id={id}
          content={content}
          isOpen={showEditModal}
          onRequestClose={handleShowEditModal}
        />
      )}
    </div>
  );
};
