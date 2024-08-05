import { useMemo, useState } from "react";
import { Edit, Trash } from "lucide-react";
import { confirmModal } from "../modals/ConfirmModal";

interface CommentProps {
  content: any;
  author: any;
  createdAt: any;
}

export const Comment: React.FC<CommentProps> = ({
  author,
  content,
  createdAt,
}) => {
  const [showEditModal, setShowEditModal] = useState(true);
  const handleShowEditModal = () => {
    setShowEditModal(!showEditModal);
  };
  const dt = useMemo(() => new Date(createdAt), []);
  const isCreator = true;
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
        {isCreator ? (
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
                  () => {
                    // callback function executed when the user confirms actions
                  }
                );
              }}
            />
          </>
        ) : null}
      </div>
      {/* {showEditModal && (
        <EditCommentModal
          content={content}
          isOpen={showEditModal}
          onRequestClose={handleShowEditModal}
        />
      )} */}
    </div>
  );
};
