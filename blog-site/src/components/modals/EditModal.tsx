import { Button } from "../ui/button";
import { Modal } from "../ui/Modal";
import { Textarea } from "../ui/textarea";
import { useState } from "react";

interface EditCommentModalProps {
  isOpen: boolean;
  content: string;
  onRequestClose: () => void;
}

export const EditCommentModal: React.FC<EditCommentModalProps> = ({
  isOpen,
  onRequestClose,
  content,
}) => {
  const [comment, setComment]: any = useState("");
  return (
    <Modal onRequestClose={onRequestClose} isOpen={isOpen}>
      <div className="flex flex-col gap-4">
        <h2 className="text-xl font-semibold">Edit comment</h2>
        <Textarea
          // defaultValue={content}
          onChange={(e) => setComment(e.target.value)}
        />
        <Button>Save</Button>
      </div>
    </Modal>
  );
};
