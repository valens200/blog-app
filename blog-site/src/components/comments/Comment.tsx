import { useMemo } from "react";
import { format } from "date-fns";

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
  const dt = useMemo(() => new Date(createdAt), []);

  return (
    <div className="flex gap-4">
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
  );
};
