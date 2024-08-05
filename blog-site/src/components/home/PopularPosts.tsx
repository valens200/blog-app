import { format } from "date-fns";
import { Link } from "react-router-dom";

export const PopularPosts: React.FC<any> = ({ posts }) => {
  return (
    <div className="space-y-3">
      <div>
        <h2 className="text-xl">popular</h2>
      </div>
      <div className="flex flex-col gap-4 divide-y-2 divide-muted">
        {posts.map((post: any, index: any) => (
          <Link
            key={index}
            to={`/p/${post.id}`}
            className="flex flex-col gap-4"
          >
            <div key={index} className="flex gap-5 sm:flex-row flex-col">
              <img
                src="https://github.blog/wp-content/uploads/2024/07/AI-DarkMode-2-1.png?w=1200"
                alt="Image"
                className="sm:max-w-[160px] w-full sm:h-32 h-52 rounded-md"
              />
              <div className="flex flex-col gap-2">
                <h2 className="text-lg font-bold text-balance max-w-">
                  {post.title}
                </h2>
                <p className="text-sm">{post.content}</p>
                <div className="flex gap-3">
                  <p>Taylor Berau</p>
                  <p>{format(new Date(post.dateTime), "yyyy MMMM ddd")}</p>
                </div>
              </div>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
};
