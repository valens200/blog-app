import { format } from "date-fns";
import { Link } from "react-router-dom";

export const FeaturedPosts: React.FC<any> = ({ posts }) => {
  return (
    <div className="flex flex-col gap-4 justify-between">
      {posts.map((post: any, index: any) => (
        <Link
          key={index}
          to={`/p/${post.id}`}
          className="flex gap-5 sm:flex-row flex-col"
        >
          <img
            src="https://github.blog/wp-content/uploads/2023/11/Security-LightMode-4.png?w=200"
            alt="Image"
            className="sm:max-w-[168px] w-full sm:h-36 h-52 rounded-md"
          />
          <div className="flex flex-col gap-2">
            <h2 className="text-lg font-bold text-balance max-w-">
              {post.title}
            </h2>
            <p className="text-sm">{post.content}</p>
            <div className="flex gap-3">
              <p>{post.author.email}</p>
              <p>{format(new Date(post.dateTime), "yyyy MMMM ddd")}</p>
            </div>
          </div>
        </Link>
      ))}
    </div>
  );
};
