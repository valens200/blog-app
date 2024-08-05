import { format } from "date-fns";
import { Link, useNavigate } from "react-router-dom";

export const FeaturedPosts: React.FC<any> = ({ posts }) => {
  const navigate = useNavigate();
  return (
    <div>
      <div>
        <div className="w-full  justify-end items-end flex">
          <button
            onClick={() => navigate(`/admin/new-post`)}
            className="flex border border-black p-2 rounded flex-row items-center"
          >
            <span> Add Post</span>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={4}
              stroke="currentColor"
              className="size-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
              />
            </svg>
          </button>
        </div>
      </div>
      <div className="flex flex-col gap-4 justify-between">
        {posts.map((post: any, index: any) => (
          <Link
            key={index}
            to={`/p/${post.id}`}
            className="flex gap-5 sm:flex-row flex-col"
          >
            <img
              src={
                post.imageUrl != null
                  ? post.imageUrl
                  : "https://github.blog/wp-content/uploads/2023/11/Security-LightMode-4.png?w=200"
              }
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
    </div>
  );
};
