USE [FTS]
GO
/****** Object:  Table [dbo].[Vehicle]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehicle](
	[VehicleID] [int] NOT NULL,
	[Number] [nvarchar](50) NOT NULL,
	[Type] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Vehicle] PRIMARY KEY CLUSTERED 
(
	[VehicleID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] NOT NULL,
	[RoleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[PaymentID] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Discription] [nvarchar](50) NOT NULL,
	[PaymentType] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Payment] PRIMARY KEY CLUSTERED 
(
	[PaymentID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Owner]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Owner](
	[OwnerID] [int] NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NULL,
	[Sex] [int] NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NULL,
	[IsActive] [bit] NOT NULL,
	[CreateBy] [nvarchar](50) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[UpdateBy] [nvarchar](50) NOT NULL,
	[UpdateTime] [datetime] NOT NULL,
 CONSTRAINT [PK_Owner] PRIMARY KEY CLUSTERED 
(
	[OwnerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderStatus](
	[OrderStatusID] [int] NOT NULL,
	[StatusName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[OrderStatusID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[EmployeeID] [int] NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[Sex] [int] NOT NULL,
	[Image] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[EmployeeID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GoodsCategory]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GoodsCategory](
	[GoodsCategoryID] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_GoodsCategory] PRIMARY KEY CLUSTERED 
(
	[GoodsCategoryID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Goods]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Goods](
	[GoodsID] [int] NOT NULL,
	[Type] [nvarchar](50) NOT NULL,
	[Weight] [int] NULL,
	[Price] [nchar](10) NULL,
	[PickupTime] [datetime] NULL,
	[PickupAddress] [nchar](10) NULL,
	[DeliveryTime] [datetime] NOT NULL,
	[DeliveryAddress] [nvarchar](50) NOT NULL,
	[Notes] [nvarchar](250) NULL,
	[OwnerID] [int] NOT NULL,
	[GoodsCategoryID] [int] NOT NULL,
 CONSTRAINT [PK_Goods_1] PRIMARY KEY CLUSTERED 
(
	[GoodsID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Driver]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Driver](
	[DriverID] [int] NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NULL,
	[Sex] [int] NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[CreateBy] [nvarchar](50) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[UpdateBy] [nvarchar](50) NOT NULL,
	[UpdateTime] [datetime] NOT NULL,
	[VehicleID] [int] NULL,
	[Age] [int] NULL,
	[Image] [nvarchar](50) NOT NULL,
	[Point] [int] NOT NULL,
 CONSTRAINT [PK_Driver] PRIMARY KEY CLUSTERED 
(
	[DriverID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[StaffDeliveryStatus] [nchar](10) NULL,
	[DriverDeliveryStatus] [nchar](10) NULL,
	[OwnerDeliveryStatus] [nchar](10) NULL,
	[OrderStatusID] [int] NOT NULL,
	[PaymentID] [int] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[DealID] [int] NULL,
 CONSTRAINT [PK_Order_1] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Route]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Route](
	[RouteID] [int] NOT NULL,
	[StartingPoint] [nvarchar](50) NOT NULL,
	[Marker1] [nchar](10) NULL,
	[Marker2] [nchar](10) NULL,
	[DestinationPoint] [nvarchar](50) NOT NULL,
	[StartTime] [nvarchar](50) NOT NULL,
	[FinishTime] [nvarchar](50) NOT NULL,
	[Notes] [nvarchar](250) NULL,
	[IsActive] [bit] NULL,
	[VehicleID] [int] NOT NULL,
	[DriverID] [int] NULL,
 CONSTRAINT [PK_Road] PRIMARY KEY CLUSTERED 
(
	[RouteID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [int] NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](10) NOT NULL,
	[RoleID] [int] NOT NULL,
 CONSTRAINT [PK_Account_1] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RouteGoodsCategory]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RouteGoodsCategory](
	[RouteID] [int] NOT NULL,
	[GoodsCategoryID] [int] NOT NULL,
 CONSTRAINT [PK_RoadGoodsCategory] PRIMARY KEY CLUSTERED 
(
	[RouteID] ASC,
	[GoodsCategoryID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[NotificationID] [int] NOT NULL,
	[EmailFrom] [nvarchar](50) NOT NULL,
	[EmailTo] [datetime] NOT NULL,
	[Message] [nvarchar](50) NOT NULL,
	[Status] [nchar](10) NULL,
	[CreateTime] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Notification] PRIMARY KEY CLUSTERED 
(
	[NotificationID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Deal]    Script Date: 02/02/2015 18:49:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Deal](
	[DealID] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Notes] [nvarchar](250) NOT NULL,
	[CreateTime] [nchar](10) NOT NULL,
	[OrderID] [int] NULL,
	[Sender] [int] NOT NULL,
	[RouteID] [int] NULL,
	[GoodsID] [int] NULL,
	[DriverID] [int] NULL,
	[OwnerID] [int] NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Deal] PRIMARY KEY CLUSTERED 
(
	[DealID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Account_Driver]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Driver] FOREIGN KEY([Email])
REFERENCES [dbo].[Driver] ([Email])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Driver]
GO
/****** Object:  ForeignKey [FK_Account_Employee]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Employee] FOREIGN KEY([Email])
REFERENCES [dbo].[Employee] ([Email])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Employee]
GO
/****** Object:  ForeignKey [FK_Account_Owner1]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Owner1] FOREIGN KEY([Email])
REFERENCES [dbo].[Owner] ([Email])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Owner1]
GO
/****** Object:  ForeignKey [FK_Account_Role]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
/****** Object:  ForeignKey [FK_Deal_Driver]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Deal]  WITH CHECK ADD  CONSTRAINT [FK_Deal_Driver] FOREIGN KEY([DriverID])
REFERENCES [dbo].[Driver] ([DriverID])
GO
ALTER TABLE [dbo].[Deal] CHECK CONSTRAINT [FK_Deal_Driver]
GO
/****** Object:  ForeignKey [FK_Deal_Goods]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Deal]  WITH CHECK ADD  CONSTRAINT [FK_Deal_Goods] FOREIGN KEY([GoodsID])
REFERENCES [dbo].[Goods] ([GoodsID])
GO
ALTER TABLE [dbo].[Deal] CHECK CONSTRAINT [FK_Deal_Goods]
GO
/****** Object:  ForeignKey [FK_Deal_Order]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Deal]  WITH CHECK ADD  CONSTRAINT [FK_Deal_Order] FOREIGN KEY([DealID])
REFERENCES [dbo].[Order] ([DealID])
GO
ALTER TABLE [dbo].[Deal] CHECK CONSTRAINT [FK_Deal_Order]
GO
/****** Object:  ForeignKey [FK_Deal_Owner]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Deal]  WITH CHECK ADD  CONSTRAINT [FK_Deal_Owner] FOREIGN KEY([OwnerID])
REFERENCES [dbo].[Owner] ([OwnerID])
GO
ALTER TABLE [dbo].[Deal] CHECK CONSTRAINT [FK_Deal_Owner]
GO
/****** Object:  ForeignKey [FK_Deal_Route1]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Deal]  WITH CHECK ADD  CONSTRAINT [FK_Deal_Route1] FOREIGN KEY([RouteID])
REFERENCES [dbo].[Route] ([RouteID])
GO
ALTER TABLE [dbo].[Deal] CHECK CONSTRAINT [FK_Deal_Route1]
GO
/****** Object:  ForeignKey [FK_Driver_Vehicle]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Driver]  WITH CHECK ADD  CONSTRAINT [FK_Driver_Vehicle] FOREIGN KEY([VehicleID])
REFERENCES [dbo].[Vehicle] ([VehicleID])
GO
ALTER TABLE [dbo].[Driver] CHECK CONSTRAINT [FK_Driver_Vehicle]
GO
/****** Object:  ForeignKey [FK_Goods_GoodsCategory]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_GoodsCategory] FOREIGN KEY([GoodsCategoryID])
REFERENCES [dbo].[GoodsCategory] ([GoodsCategoryID])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_GoodsCategory]
GO
/****** Object:  ForeignKey [FK_Goods_Owner]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Goods]  WITH CHECK ADD  CONSTRAINT [FK_Goods_Owner] FOREIGN KEY([OwnerID])
REFERENCES [dbo].[Owner] ([OwnerID])
GO
ALTER TABLE [dbo].[Goods] CHECK CONSTRAINT [FK_Goods_Owner]
GO
/****** Object:  ForeignKey [FK_Notification_Account]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD  CONSTRAINT [FK_Notification_Account] FOREIGN KEY([EmailFrom])
REFERENCES [dbo].[Account] ([Email])
GO
ALTER TABLE [dbo].[Notification] CHECK CONSTRAINT [FK_Notification_Account]
GO
/****** Object:  ForeignKey [FK_Order_OrderStatus]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_OrderStatus] FOREIGN KEY([OrderStatusID])
REFERENCES [dbo].[OrderStatus] ([OrderStatusID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_OrderStatus]
GO
/****** Object:  ForeignKey [FK_Order_Payment]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Payment] FOREIGN KEY([PaymentID])
REFERENCES [dbo].[Payment] ([PaymentID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Payment]
GO
/****** Object:  ForeignKey [FK_Route_Driver]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Route]  WITH CHECK ADD  CONSTRAINT [FK_Route_Driver] FOREIGN KEY([DriverID])
REFERENCES [dbo].[Driver] ([DriverID])
GO
ALTER TABLE [dbo].[Route] CHECK CONSTRAINT [FK_Route_Driver]
GO
/****** Object:  ForeignKey [FK_Route_Vehicle]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[Route]  WITH CHECK ADD  CONSTRAINT [FK_Route_Vehicle] FOREIGN KEY([VehicleID])
REFERENCES [dbo].[Vehicle] ([VehicleID])
GO
ALTER TABLE [dbo].[Route] CHECK CONSTRAINT [FK_Route_Vehicle]
GO
/****** Object:  ForeignKey [FK_RoadGoodsCategory_GoodsCategory]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[RouteGoodsCategory]  WITH CHECK ADD  CONSTRAINT [FK_RoadGoodsCategory_GoodsCategory] FOREIGN KEY([GoodsCategoryID])
REFERENCES [dbo].[GoodsCategory] ([GoodsCategoryID])
GO
ALTER TABLE [dbo].[RouteGoodsCategory] CHECK CONSTRAINT [FK_RoadGoodsCategory_GoodsCategory]
GO
/****** Object:  ForeignKey [FK_RoadGoodsCategory_Road]    Script Date: 02/02/2015 18:49:55 ******/
ALTER TABLE [dbo].[RouteGoodsCategory]  WITH CHECK ADD  CONSTRAINT [FK_RoadGoodsCategory_Road] FOREIGN KEY([RouteID])
REFERENCES [dbo].[Route] ([RouteID])
GO
ALTER TABLE [dbo].[RouteGoodsCategory] CHECK CONSTRAINT [FK_RoadGoodsCategory_Road]
GO
