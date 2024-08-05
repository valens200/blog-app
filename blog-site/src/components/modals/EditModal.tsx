import { getErrorFromResponse } from "@/utils/functions/function";
import { Button } from "../ui/button";
import { Modal } from "../ui/Modal";
import { Textarea } from "../ui/textarea";
import { useState } from "react";
import toast from "react-hot-toast";
import { authApi } from "@/utils/api/constants";

interface EditCommentModalProps {
  isOpen: boolean;
  content: string;
  id: any;
  onRequestClose: () => void;
}

export const EditCommentModal: React.FC<EditCommentModalProps> = ({
  isOpen,
  onRequestClose,
  content,
  id,
}) => {
  const [comment, setComment]: any = useState("");
  const [loading, setLoading] = useState(false);
  const submit = () => {
    if (comment == "" || comment == null) {
      toast.error("The comment should not be null");
      return;
    }
    try {
      setLoading(true);
      setTimeout(async () => {
        const response = await authApi.put(`comments/update/${id}`, {
          content: comment,
          title: comment,
        });
        console.log(response);
        toast.success("The comment was updated successfully");
        onRequestClose();
        window.location.href = window.location.href;
      }, 2000);
    } catch (error) {
      toast.error(getErrorFromResponse(error));
    } finally {
      setLoading(false);
    }
  };
  return (
    <Modal onRequestClose={onRequestClose} isOpen={isOpen}>
      <div className="flex flex-col gap-4">
        <h2 className="text-xl font-semibold">Edit comment</h2>
        <Textarea
          defaultValue={content}
          onChange={(e) => setComment(e.target.value)}
        />
        <Button disabled={loading} onClick={submit}>
          Save
        </Button>
      </div>
    </Modal>
  );
};
