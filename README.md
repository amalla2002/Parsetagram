# Project 3 - *Parsetagram*

**Name of your app** is a photo sharing app using Parse as its backend.

Time spent: *20* hours spent in total

## User Stories

The following **required** functionality is completed:

- [Y] User sees app icon in home screen.
- [Y] User can sign up to create a new account using Parse authentication
- [Y] User can log in to their account
- [Y] The current signed in user is persisted across app restarts
- [Y] User can log out of their account
- [Y] User can take a photo, add a caption, and post it to "Instagram"
- [Y] User can view the last 20 posts submitted to "Instagram"
- [Y] User can pull to refresh the last 20 posts submitted to "Instagram"
- [Y] User can tap a post to go to a Post Details activity, which includes timestamp and caption.

The following **stretch** features are implemented:

- [ ] Style the login page to look like the real Instagram login page.
- [Y] Style the feed to look like the real Instagram feed.
- [Y] User can load more posts once they reach the bottom of the feed using endless scrolling.
- [Y] User should switch between different tabs using fragments and a Bottom Navigation View.
  - [Y] Feed Tab (to view all posts from all users)
  - [Y] Capture Tab (to make a new post using the Camera and Photo Gallery)
  - [Y] Profile Tab (to view only the current user's posts, in a grid)
- [ ] Show the username and creation time for each post
- User Profiles:
  - [ ] Allow the logged in user to add a profile photo
  - [ ] Display the profile photo with each post
  - [ ] Tapping on a post's username or profile photo goes to that user's profile page
  - [Y] User Profile shows posts in a grid
	I have added infinite scrolling to this feature
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [Y] User can like a post and see number of likes for each post in the post details screen.
	I have this on the feed like the real instagram does as of Friday 17, 2022.
The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:
![Screen_Recording_2022-06-17_at_4_02_39_PM_AdobeExpress](https://user-images.githubusercontent.com/96102973/174411346-a4d8a293-1ce6-4891-b431-cd7437204dd7.gif)


## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Android Async Http Client](http://loopj.com/android-async-http/) - networking library


## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [2022] [Alejandro Malla]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
