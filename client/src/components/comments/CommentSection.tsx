import { Comment } from "./Comment";
import { CommentSectionInput } from "./CommentSectionInput";

interface CommentSectionProps {
  postId: string;
  comments: any[];
}
export const CommentSection: React.FC<CommentSectionProps> = ({
  postId,
  comments,
}) => {
  return (
    <div className="mt-3 space-y-5 border-t border-t-border">
      <h2 className="font-semibold mt-3">{`Comments (${comments.length})`}</h2>
      <CommentSectionInput postId={postId} userId="" />
      <div className="flex flex-col gap-5">
        {comments.map((comment: any, index: number) => (
          <div key={index}>
            {comment.visibility == "VISIBLE" && (
              <Comment
                id={comment.id}
                author={comment.author}
                content={comment.title}
                createdAt={comment.dateTime}
              />
            )}
          </div>
        ))}
      </div>
    </div>
  );
};
