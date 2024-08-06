import { getErrorFromResponse } from "@/utils/functions/function";
import { Textarea } from "../ui/textarea";
import { authApi } from "@/utils/api/constants";
import React, { useState } from "react";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
interface CommentSectionInputProps {
  postId: string;
  userId: string;
}

export const CommentSectionInput: React.FC<CommentSectionInputProps> = ({
  postId,
}) => {
  const [comment, setComment]: any = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (comment == "" || comment.length == 0 || comment == undefined) {
      toast.error("The comment should not be null");
      return;
    }
    try {
      const response = await authApi.post("/comments/create", {
        title: comment,
        content: comment,
        postId: postId,
      });
      toast.success("The comment added successfully");
      window.location.href = window.location.href;
    } catch (error) {
      toast.error(getErrorFromResponse(error));
    }
  };
  return (
    <div className="flex gap-3 items-start">
      <div className="flex justify-center items-center bg-muted text-muted-foreground rounded-full h-12 w-12">
        <h1>PG</h1>
      </div>
      <Textarea
        defaultValue={comment}
        onChange={(e) => setComment(e.target.value)}
        placeholder="What's your comment on this?"
        rows={3}
      />
      <button
        onClick={handleSubmit}
        className="bg-black text-white p-2 rounded"
      >
        Post
      </button>
    </div>
  );
};
